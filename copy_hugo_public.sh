#!/bin/bash

# 清空目标文件夹
rm -rf yao_blog/*
mkdir -p yao_blog

# 复制 public 内容到目标文件夹，排除 CNAME 文件
rsync -av --exclude 'CNAME' public/ yao_blog/
