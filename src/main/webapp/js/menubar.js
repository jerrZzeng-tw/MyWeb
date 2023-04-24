var menuConfig = [
{
    'height':  15, /*第一層*/
    'width' : 140,
    'firstX' : 2,
    'firstY' : 0,
    'nextX' : 0,
    'hideAfter' : 200,
    'css'   : 'gurtl0o',
    'trace' : true
},
{
    'height':  22, /*第二層*/
    'width' : 270,
    'firstY' : 26, /*第二層選單 to TOP 之距離*/
    'firstX' :  0,
    'nextY' : -1,
    'css' : 'gurtl1o'
},
{
    'firstX' : 201, /*第三層*/
    'height':  22,
    'firstY' : 0
}
];

new menu (menuHierarchy, menuConfig);
