---
title: "CCNA"
date: 2026-01-06
lastmod: 2026-01-07
draft: true
tags: ["CCNA","Networking"]
author: "Yao Lu"
---





# How to config

## Prerequisites
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


       

                                                                                                                                                                                                                 
