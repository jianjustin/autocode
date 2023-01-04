package main

import (
	"fmt"
	"github.com/jianjustin/code_generate_tools/model"
	"github.com/spf13/viper"
	"os"
	"path/filepath"
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
	fmt.Println("当前项目根目录: " + model.GLOBAL_PATH)

	_, err2 := os.Stat(model.GLOBAL_PATH + model.OUTPUT_PATH)
	if !os.IsNotExist(err2) {
		os.RemoveAll(model.GLOBAL_PATH + model.OUTPUT_PATH)
	}
	os.Mkdir(model.GLOBAL_PATH+model.OUTPUT_PATH, os.ModePerm)

	filepath.Walk(model.GLOBAL_PATH+model.TEMPLATE_PATH, func(path string, info os.FileInfo, err error) error {
		if err != nil {
			fmt.Println(err)
			return err
		}
		fmt.Printf("dir: %v: name: %s\n", info.IsDir(), path)
		return nil
	})
}
