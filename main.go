package main

import (
	"fmt"
	"github.com/spf13/viper"
)

// 项目配置
type AppConfig struct {
	AppName     string
	Port        int
	Description string
}

var conf AppConfig

// 初始化配置文件
func init() {
	viper.SetConfigName("config")
	viper.SetConfigType("toml")
	viper.AddConfigPath("/etc/appname/")  // 查找配置文件所在路径
	viper.AddConfigPath("$HOME/.appname") // 多次调用AddConfigPath，可以添加多个搜索路径
	viper.AddConfigPath(".")              // 还可以在工作目录中搜索配置文件
	viper.AddConfigPath("./conf")         // 还可以在工作目录中搜索配置文件
	if err := viper.ReadInConfig(); err != nil {
		fmt.Printf("read config failed: %v \r", err)
	}
	if err := viper.Unmarshal(&conf); err != nil {
		fmt.Println(err)
	}
}

func main() {
	fmt.Println("AppName: " + conf.AppName)
}
