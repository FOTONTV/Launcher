#!/bin/sh
git clone https://github.com/FOTONTV/Launcher.git
cd Launcher
sed -i 's/git@github.com:/https:\/\/github.com\//' .gitmodules
git submodule sync
git submodule update --init --recursive