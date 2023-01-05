package model

// AppConfig 配置
type AppConfig struct {
	AppName     string
	Port        int
	Description string

	Service     string
	Model       string
	Package     string
	ServiceName string
	Request     string
	Where       string
}
