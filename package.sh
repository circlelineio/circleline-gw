#!/bin/sh
./activator assembly
docker build -t circleline-gw .