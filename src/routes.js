const path = require('path');
const boom = require('@hapi/boom');

const routes = [
    {
        method: 'GET',
        path: '/',
        handler: (request, h) => {
            return h.file('index.html');
        },
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static'),
            }
        }
    },
    {
        method: 'GET',
        path: '/index.css',
        handler: (request, h) => {
            return h.file('index.css');
        },
        options: {
            files: {
                relativeTo: path.join(__dirname, 'static'),
            }
        }
    },
]
module.exports = routes;