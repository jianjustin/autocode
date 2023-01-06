package tool

import (
	"fmt"
	"github.com/jianjustin/code_generate_tools/model"
	"github.com/spf13/viper"
	"strings"
)

var (
	CONF = BuildConfig()
)

func BuildConfig() model.AppConfig {
	c := GetAppConfig()

	c.Service1 = strings.Title(c.Service)
	c.Package1 = strings.Title(c.Package)
	c.PageName1 = strings.Title(c.PageName)
	c.Request = strings.ReplaceAll("GetServiceList", "Service", strings.Title(c.Service))
	c.Where = strings.ReplaceAll("ServiceWhere", "Service", c.Service)

	return c
}

// GetAppConfig 初始化配置文件
func GetAppConfig() model.AppConfig {
	var conf model.AppConfig

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
	return conf
}
