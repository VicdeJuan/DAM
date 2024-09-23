"" CONFIGURACIÓN PARA QUE SE VEA VIM EN EL PROYECTOR

set background=light

" Colores brillantes y de alto contraste
syntax on
colorscheme morning " desert, elflord murphy
highlight Comment ctermfg=DarkMagenta guifg=DarkMagenta
highlight Identifier ctermfg=Cyan guifg=Cyan
highlight Statement ctermfg=Red guifg=Red
highlight PreProc ctermfg=Yellow guifg=Yellow
highlight Type ctermfg=Green guifg=Green
highlight Special ctermfg=Magenta guifg=Magenta
highlight Constant ctermfg=Blue guifg=Blue

 " Incrementa el tamaño del cursor para que sea visible
set guicursor=n-v-c:block-Cursor/lCursor

" " Cursor más visible
set cursorline
highlight CursorLine term=bold cterm=bold guibg=lightgrey
