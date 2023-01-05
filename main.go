package main

import (
	"fmt"
	"github.com/jianjustin/code_generate_tools/model"
	"github.com/jianjustin/code_generate_tools/tool"
	"os"
	"path/filepath"
)

func main() {
	conf := tool.BuildConfig()
	fmt.Println("AppName: " + conf.AppName)
	fmt.Println("当前项目根目录: " + model.GLOBAL_PATH)

	_, err2 := os.Stat(model.GLOBAL_PATH + model.OUTPUT_PATH)
	if !os.IsNotExist(err2) {
		os.RemoveAll(model.GLOBAL_PATH + model.OUTPUT_PATH)
	}
	os.Mkdir(model.GLOBAL_PATH+model.OUTPUT_PATH, os.ModePerm)

	filepath.Walk(model.GLOBAL_PATH+model.TEMPLATE_PATH, tool.GenerateFile)
}
