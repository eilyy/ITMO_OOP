cmake_minimum_required(VERSION 3.15)
project(INI)

set(CMAKE_CXX_STANDARD 14)

add_executable(INI main.cpp INIFileReader.cpp INIFileReader.h INIParser.cpp INIParser.h INIController.cpp INIController.h)