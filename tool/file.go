package tool

import (
	"fmt"
	"github.com/jianjustin/code_generate_tools/model"
	"os"
	"text/template"
)

func GenerateFile(path string, info os.FileInfo, err error) error {
	if err != nil || info.IsDir() {
		return err
	}

	temp, _ := template.ParseFiles(model.GLOBAL_PATH + model.TEMPLATE_PATH + info.Name())

	f, err := os.OpenFile(model.GLOBAL_PATH+model.OUTPUT_PATH+info.Name(), os.O_CREATE|os.O_WRONLY, 0o777)
	if err != nil {
		return err
	}
	if err = temp.Execute(f, CONF); err != nil {
		return err
	}
	_ = f.Close()

	fmt.Printf("dir: %v: name: %s\n", info.IsDir(), path)
	return nil
}

func BuildOutputPath() error {
	_, err2 := os.Stat(model.GLOBAL_PATH + model.OUTPUT_PATH)
	if !os.IsNotExist(err2) {
		os.RemoveAll(model.GLOBAL_PATH + model.OUTPUT_PATH)
	}
	os.Mkdir(model.GLOBAL_PATH+model.OUTPUT_PATH, os.ModePerm)
	return nil
}
