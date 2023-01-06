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

	AddFunc  string
	EditFunc string
	DelFunc  string

	PageName  string
	PageName1 string

	SearchField []interface{}
	TableField  []interface{}
	FormField   []interface{}
	Apis        []interface{}
}
