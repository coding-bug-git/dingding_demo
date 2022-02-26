const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path')
module.exports = {
  entry: './src/index.js',
  output: {
    clean: true,
    path: path.resolve(__dirname, 'dist'),
    filename: 'bundle.js'
    // environment: {
    //   arrowFunction: false
    // }
  },
  plugins: [new HtmlWebpackPlugin({
    template: './src/index.html'
  })]


}