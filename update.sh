#!/bin/bash

hugo
git status
git pull
git status
git add .
git status
git commit -am "update content"
git status
git push
git status
