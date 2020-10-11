//
// Created by Илья Евдокимов on 11.10.2020.
//

#ifndef INI_INIPARSER_H
#define INI_INIPARSER_H

#include "INIFileReader.h"
#include <map>


class INIParser {
    void makeMap();
    INIFileReader f;
public:
    INIParser(const string& file);
    map<string, map<string, string>> data;
};


#endif //INI_INIPARSER_H
