#!/bin/bash
nohup sudo kill -15 $(lsof -t -i:8080) &
