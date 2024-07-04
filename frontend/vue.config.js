const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({

    transpileDependencies: true,
    outputDir: "../src/main/resources/static",  // 빌드 타겟 디렉토리
    devServer: {
        // 개발 서버가 모든 네트워크 인터페이스에서 수신 대기
        host: '0.0.0.0',
        proxy: {
            // 로컬 개발 환경
            '/api': {
                target: 'http://localhost:8080',
                changeOrigin: true // cross origin 허용
            }
            // 원격 개발 환경
            // '/api': {
            //     target: 'http://192.168.1.100:8080',
            //     changeOrigin: true // cross origin 허용
            // }
        }
    }
})