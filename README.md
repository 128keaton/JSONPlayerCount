
![do you see what I see?](https://github.com/128keaton/JSONPlayerCount/raw/master/src/github/img/JSONPlr.png "")

Bukkit player count to a JSON file

## Installation:
### From source:
Open in Eclipse, export as JAR with libraries (lib folder)
Place exported jar into plugins/
Reload and the stats.json will be in the server's root directory.
### Pre-compiled release:
Place exported jar into plugins/
Reload and the stats.json will be in the server's root directory.

### Web front-end:
Drag the contents of the `web front-end` folder into a folder hosted on your web server, e.g `/var/www/mc-status`.
Edit `minecraft.js` to include your URLs for `players.php`.
Then, edit `decode.php` to include the URL for your `stats.json`
Personally, I host my website on a remote server, different from my MC server. I forward *:80/*:25565 to my local, MC server (play.128keaton.com), but I needed a way to get my `stats.json` readable from my web server. I simply made an alias to http://play.128keaton.com/stat.json using Apache (Use Google). And to get around Ajax errors, I made a file on my web server to read and return JSON, making it appear to have come from http://128keaton.com/*. 

## Examples:
[Raw Output](http://128keaton.com/applecider/players.php)

[Real time usage](http://128keaton.com/applecider/)

## Bukkit Dev:
Also, the plugin is available on [Bukkit Dev](JSONPlayercounter)
