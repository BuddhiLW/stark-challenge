#!/usr/bin/env sh

curl  http://localhost:4040/api/tunnels | egrep -o 'https?://[^ ]+' | sed 's/".*//'
