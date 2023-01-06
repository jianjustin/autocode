package updf

import (
	"admin/global"
	"admin/model/common/request"
	"admin/model/updf/"
	"admin/utils"
	"gorm.io/gorm"
)

type UpdfService struct{}

// GetCount 获取数量
func (exa *UpdfService) GetCount(args request.GetList) (err error, total int64) {
	db := global.GVA_DBList[global.UPDF].Model(&.{})

	exa.Where(args, db)

	err = db.Debug().Count(&total).Error
	if err != nil {
		return err, total
	}

	return err, total
}

// GetList 分页获取列表
func (exa *UpdfService) GetList(args request.GetList) (err error, list *[].) {
	limit := args.PageSize
	offset := args.PageSize * (args.Page - 1)
	db := global.GVA_DBList[global.UPDF].Model(&.{})

	exa.Where(args, db)

	var listDb [].
	err = db.Debug().Limit(limit).Offset(offset).Find(&listDb).Error

	return err, &listDb
}

// GetAllList 获取所有列表
func (exa *UpdfService) GetAllList(args request.GetList) (err error, list *[].) {
	db := global.GVA_DBList[global.UPDF].Model(&.{})

	exa.Where(args, db)

	var listDb [].
	err = db.Debug().Find(&listDb).Error

	return err, &listDb
}

// GetCountAndList 获取数量及分页信息
func (exa *UpdfService) GetCountAndList(args request.GetList) (err error, list *[]., total int64) {
	limit := args.PageSize
	offset := args.PageSize * (args.Page - 1)
	db := global.GVA_DBList["updf"].Model(&.{})

	exa.Where(args, db)
	err = db.Count(&total).Error
	if err != nil {
		return err, list, total
	}

	var listDb [].
	err = db.Debug().Limit(limit).Offset(offset).Find(&listDb).Error

	return err, &listDb, total
}

func (exa *UpdfService) Where(args request.GetList, db *gorm.DB) {
	utils.IsExist(db)
}