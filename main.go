package main

import (
	"github.com/jianjustin/code_generate_tools/model"
	"github.com/jianjustin/code_generate_tools/tool"
	"path/filepath"
)

func main() {
	tool.BuildOutputPath()
	filepath.Walk(model.GLOBAL_PATH+model.TEMPLATE_PATH, tool.GenerateFile)
}
