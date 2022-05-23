#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <dirent.h>
#include <fstream>
#include <iostream>
using namespace std;

int main(int argc, char **argv) {
    string dir_path = string(argv[1]);
    const char *file_path;
    ifstream reading;
    ofstream writing;
    string line;
    int line_length;
    bool mulCom, stringPart, charPart;

    DIR *dirp = opendir(dir_path.c_str());
    struct dirent *ent;
    struct stat data;
    while (ent = readdir(dirp)) {
        file_path = (dir_path + ent->d_name).c_str();
        if (stat(file_path, &data) == 0 && S_ISREG(data.st_mode) &&
            ent->d_namlen >= 2 && ent->d_name[ent->d_namlen - 2] == '.' &&
                                  ent->d_name[ent->d_namlen - 1] == 'c') {
            reading.open(file_path);
            writing.open("./" + string(ent->d_name));
            mulCom = false;
            while (getline(reading, line)){
                stringPart = false;
                charPart = false;
                line += '\n';
                line_length = line.length();
                for (size_t i = 0; i < line_length; i++) {
                    if (mulCom) {
                        if (line[i] == '*' && i + 2 < line_length && line[i + 1] == '/') {
                            mulCom = false;
                            i++;
                        }
                    } else if (stringPart) {
                        writing << line[i];
                        if (line[i] == '"' && i != 0 && line[i - 1] != '\\')
                            stringPart = false;
                    } else if (charPart) {
                        writing << line[i];
                        if (line[i] == '\'' && i != 0 && line[i - 1] != '\\')
                            charPart = false;
                    } else if (line[i] == '"') {
                        writing << line[i];
                        stringPart = true;
                    } else if (line[i] == '\'') {
                        writing << line[i];
                        charPart = true;
                    } else if (line[i] == '/' && i + 2 < line_length && (line[i + 1] == '*' || line[i + 1] == '/')) {
                        if (line[i + 1] == '*')
                            mulCom = true;
                        else {
                            writing << endl;
                            break;
                        }
                    } else {
                        if (line[i] == '"')
                            stringPart = true;
                        writing << line[i];
                    }
                }
            }
            writing.close();
        }
    }
    closedir(dirp);
}
