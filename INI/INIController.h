//
// Created by Илья Евдокимов on 11.10.2020.
//

#ifndef INI_INICONTROLLER_H
#define INI_INICONTROLLER_H

#include "INIParser.h"
#include <sstream>


class INIController {
    INIParser p;
public:
    INIController(const string& file);
    template <typename T>
    T INISearch(const string& Section, const string& Name) {
        T result;
        if(p.data.find(Section) == p.data.end()) {
            throw "Section not found";
        }
        if(p.data[Section].find(Name) == p.data[Section].end()) {
            throw "Field not found";
        }
        string value = p.data[Section][Name];
        stringstream ss;
        ss << value;
        if(typeid(T) == typeid(string))
            ss >> result;
        else {
            for (int i = 0; i < value.length(); i++) {
                if (!isdigit(value[i]) && value[i] != '.') {
                    throw "Invalid Type";
                }
            }
            ss >> result;
        }
        return result;
    };
};


#endif //INI_INICONTROLLER_H
