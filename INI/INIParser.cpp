//
// Created by Илья Евдокимов on 11.10.2020.
//

#include "INIParser.h"

INIParser::INIParser(const string& file) : f(file) {
    this->makeMap();
}

void INIParser::makeMap() {
    string str, CurrentSection, CurrentName, Value;
    while(f.fin >> str) {
        if(str[0] == '[')
            CurrentSection = str.substr(1, str.find(']') - 1);
        else if(str == "=") {
            streampos pos = f.fin.tellg();
            f.fin >> str;
            Value = str;
            f.fin.seekg(pos);
            getline(f.fin, str);
            this->data[CurrentSection][CurrentName] = Value;
        }
        else
            CurrentName = str;
    }
}