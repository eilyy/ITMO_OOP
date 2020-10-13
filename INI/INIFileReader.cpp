//
// Created by Илья Евдокимов on 11.10.2020.
//

#include "INIFileReader.h"

INIFileReader::INIFileReader(const string& file) {
    this->fin.open(file);
    if(!fin) {
        throw "Error while opening file";
    }
}