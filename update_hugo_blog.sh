#!/bin/bash

hugo
git pull
git status
git add .
git commit -am "update content"
git status
git push


# 保存 .git 文件夹和 CNAME 文件
mv /home/yaolu/fromGithub/yao_blog/.git /tmp
mv /home/yaolu/fromGithub/yao_blog/CNAME /tmp


# # 清空目标文件夹
# rm -rf /home/yaolu/fromGithub/yao_blog/*


# 清空目标文件夹下的所有文件，除了 .git 和 CNAME
find /home/yaolu/fromGithub/yao_blog -mindepth 1 ! -name '.git' ! -name 'CNAME' -delete

# 将 .git 和 CNAME 文件移回目标文件夹
mv /tmp/.git /home/yaolu/fromGithub/yao_blog
mv /tmp/CNAME /home/yaolu/fromGithub/yao_blog

# 复制 public 内容到目标文件夹
cp -r public/* /home/yaolu/fromGithub/yao_blog

cd /home/yaolu/fromGithub/yao_blog

git pull
git status
git add .
git commit -am "update content"
git status
git push
