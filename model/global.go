package model

import "os"

var (
	GLOBAL_PATH, _ = os.Getwd()
)

const (
	OUTPUT_PATH   = "/output/"
	TEMPLATE_PATH = "/template/"
)
