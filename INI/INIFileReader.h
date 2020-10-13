//
// Created by Илья Евдокимов on 11.10.2020.
//

#ifndef INI_INIFILEREADER_H
#define INI_INIFILEREADER_H

#include <fstream>
#include <iostream>
#include <exception>

using namespace std;


class INIFileReader {
public:
    ifstream fin;
    INIFileReader(const string& file);
};


#endif //INI_INIFILEREADER_H
