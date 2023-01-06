package model

// AppConfig 配置
type AppConfig struct {
	Service     string
	Service1    string //大写
	Model       string
	Package     string
	Package1    string //大写
	Request     string
	ServiceName string
	Where       string

	ApiGroup  string
	ApiFunc   string
	ApiMethod string

	PageName string

	SearchField []interface{}
	TableField  []interface{}
}
