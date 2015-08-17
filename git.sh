#!/bin/bash
echo "Please input Message:"
read ans
cd PHDviewer
git pull
git commit -m "$ans"
git push

