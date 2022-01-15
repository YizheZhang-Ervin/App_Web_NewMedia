module.exports = {
    publicPath: '/',
    outputDir: 'dist',
    devServer: {
        proxy: {
            '/': {
                target: 'http://127.0.0.1:8081',
                ws: true,
                secure: false,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}