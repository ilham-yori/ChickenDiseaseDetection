const path = require('path');
const boom = require('@hapi/boom');
const fs = require('fs');
const { nanoid } = require('nanoid');
const { 
    mainPage, mainPageCSS, predictCSS, predictPage, aboutPage, aboutPageCSS, indexhbsCSS, uploadImage
} = require('./handler');

const routes = [
    {
        method: 'GET',
        path: '/',
        handler: mainPage,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static'),
            }
        }
    },
    {
        method: 'GET',
        path: '/predict',
        handler: predictPage,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static')
            }
        }
    },
    {
        method: 'GET',
        path: '/about',
        handler: aboutPage,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static')
            }
        }
    },
    {
        method: 'POST',
        path: '/predict',
        handler: uploadImage,
        options: {
            payload: {
                output: 'stream',
                multipart: true,
            },
            files: {
                relativeTo: path.join(__dirname, 'static')
            }
        }
    },
    {
        method: 'GET',
        path: '/predict/{file*}',
        handler: {
            directory: {
                path: 'src/Upload'
            }
        }
    },
    {
        method: 'GET',
        path: '/index.css',
        handler: mainPageCSS,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static'),
            }
        }
    },
    {
        method: 'GET',
        path: '/predict.css',
        handler: predictCSS,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static')
            }
        }
    },
    {
        method: 'GET',
        path: '/about.css',
        handler: aboutPageCSS,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static')
            }
        }
    },
    {
        method: 'GET',
        path: '/indexhbs.css',
        handler: indexhbsCSS,
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static')
            }
        }
    }
]
module.exports = routes;