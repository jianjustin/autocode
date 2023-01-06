package updf

import (
	"admin/global"
	"admin/model/common/request"
	"admin/model/updf/{{.Package}}"
	"admin/utils"
	"gorm.io/gorm"
)

type Updf{{.Service1}}Service struct{}

// Get{{.Service1}}Count 获取{{.ServiceName}}数量
func (exa *Updf{{.Service1}}Service) Get{{.Service1}}Count(args request.{{.Request}}) (err error, total int64) {
	db := global.GVA_DBList[global.UPDF].Model(&{{.Package}}.{{.Model}}{})

	exa.{{.Service}}Where(args, db)

	err = db.Debug().Count(&total).Error
	if err != nil {
		return err, total
	}

	return err, total
}

// Get{{.Service1}}List 分页获取{{.ServiceName}}列表
func (exa *Updf{{.Service1}}Service) Get{{.Service1}}List(args request.{{.Request}}) (err error, list *[]{{.Package}}.{{.Model}}) {
	limit := args.PageSize
	offset := args.PageSize * (args.Page - 1)
	db := global.GVA_DBList[global.UPDF].Model(&{{.Package}}.{{.Model}}{})

	exa.{{.Service}}Where(args, db)

	var listDb []{{.Package}}.{{.Model}}
	err = db.Debug().Limit(limit).Offset(offset).Find(&listDb).Error

	return err, &listDb
}

// GetAll{{.Service1}}List 获取所有{{.ServiceName}}列表
func (exa *Updf{{.Service1}}Service) GetAll{{.Service1}}List(args request.{{.Request}}) (err error, list *[]{{.Package}}.{{.Model}}) {
	db := global.GVA_DBList[global.UPDF].Model(&{{.Package}}.{{.Model}}{})

	exa.{{.Service}}Where(args, db)

	var listDb []{{.Package}}.{{.Model}}
	err = db.Debug().Find(&listDb).Error

	return err, &listDb
}

// Get{{.Service1}}CountAndList 获取{{.ServiceName}}数量及分页信息
func (exa *Updf{{.Service1}}Service) Get{{.Service1}}CountAndList(args request.{{.Request}}) (err error, list *[]{{.Package}}.{{.Model}}, total int64) {
	limit := args.PageSize
	offset := args.PageSize * (args.Page - 1)
	db := global.GVA_DBList["updf"].Model(&{{.Package}}.{{.Model}}{})

	exa.{{.Service}}Where(args, db)
	err = db.Count(&total).Error
	if err != nil {
		return err, list, total
	}

	var listDb []{{.Package}}.{{.Model}}
	err = db.Debug().Limit(limit).Offset(offset).Find(&listDb).Error

	return err, &listDb, total
}

func (exa *Updf{{.Service1}}Service) {{.Service}}Where(args request.{{.Request}}, db *gorm.DB) {
	utils.IsExist(db)
}