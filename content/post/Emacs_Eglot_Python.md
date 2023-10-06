---
title: "Emacs Eglot Python config"
date: 2023-10-06
lastmod: 2023-10-06
draft: false
author: "Yao"
---





# How to config
	The config is very sample just show as below.

# Prerequisites
  One of the python server need to be installed, for example I chosed the pyright
  ``` bash
  $ pip install pyright

  ````
 
# Emacs 
> Elisp	

``` elisp
    (use-package eglot
      :config
      (add-to-list 'eglot-server-programs
		   '(python-mode . ("pyright-langserver" "--stdio")))
      (add-hook 'python-mode-hook 'eglot-ensure))                                                                                                                                                                                                  
````		


       

                                                                                                                                                                                                                 
