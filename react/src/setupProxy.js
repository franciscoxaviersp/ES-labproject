const {createProxyMiddleware}  = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://10.10.1.4:8080',
      changeOrigin: true,
    })
  );
  app.use(
    '/test',
    createProxyMiddleware({
      target: 'http://10.10.1.7:8080',

    })
  );
};