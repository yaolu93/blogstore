---
title: "Kubernetes"
date: 2026-01-06
lastmod: 2026-01-07
draft: true
tags: ["Kubernetes","DevOps"]
author: "Yao Lu"
---





# How to config
    The config is very sample just show as below.

# Prerequisites
  One of the python server need to be installed, for example I chosed the pyright
  ``` bash
  $ pip install pyright
  ````
 
# Emacs 


``` elisp
    (use-package eglot
      :config
      (add-to-list 'eglot-server-programs
       '(python-mode . ("pyright-langserver" "--stdio")))
      (add-hook 'python-mode-hook 'eglot-ensure))                                                                                                                                                                                                  
```
## Kubernetes Archtecture
- Master Node and Work Node
-
-
-
-

       

                                                                                                                         
* todo question
1. etcd
2. Service
3. Deployment
4. Pod
5. DeamonSet
6. kubelet                                                                                          
