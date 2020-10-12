#include "INIController.h"

int main(int argc, char* argv[]) {
    if(argc != 2) {
        cerr << "Only one program argument required";
        exit(1);
    }
    string file = argv[1];
    if(file.substr(file.length() - 4, file.length()) != ".ini") {
        cerr << "INI file required (*.ini)";
        exit(1);
    }

    INIController ini(file);
    cout << ini.INISearch<string>("COMMON", "DiskCachePath") << ' ' << ini.INISearch<double>("[ADC_DEV]", "SimpleRate");
    return 0;
}
