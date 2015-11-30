
![do you see what I see?](https://github.com/128keaton/JSONPlayerCount/raw/master/src/github/img/JSONPlr.png "")

Bukkit player count to a JSON file

## Features:
* Player list with avatars!
* Compatability with older versions**
* 'Backend' status.
* Simple: only uses JSON (stats), Java (bukkit plugin), JavaScript/HTML/CSS (web front-end).
* Web front-end built using Bootstrap, looks good on any screen size.
* Player count
* Doesn't use an API service, all in house :)

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

Personally, I host my website on a remote server, different from my MC server. I forward *:80/*:25565 to my local, MC server (play.128keaton.com), but I needed a way to get my `stats.json` readable from my web server. 

I simply made an alias to http://play.128keaton.com/stat.json using Apache (Use Google). 

And to get around Ajax errors, I made a file on my web server to read and return JSON, making it appear to have come from http://128keaton.com/*. 

## Examples:
[Raw Output](http://128keaton.com/applecider/players.php)

[Real time usage](http://128keaton.com/applecider/)

## Bukkit Dev:
Also, the plugin is available on [Bukkit Dev](JSONPlayercounter)

## Notes/FAQ:
Q: Why did you do this, why not use an API service?:
A: The server I use uses 1.5.1 Spigot, which hasn't and will never use modern Minecraft server querying responses. Most, if not all, working APIs on the web right now do not support 1.5.1 query responses. I thought about writing an API with older support, but a Bukkit plugin seemed to be more fun. Plus, it allows for expandability. I could eventually add chat messages or even player health/location to the web front-end.


** (only tested with 1.5.1, but theoretically should work with most versions)

Also, I havent tested above three players, so beyond that, formatting might (no promises) get funky.
