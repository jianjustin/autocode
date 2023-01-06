package updf

import (
	"admin/global"
	"admin/model/common/request"
	"admin/model/updf/goods"
	"admin/utils"
	"gorm.io/gorm"
)

type UpdfGoodService struct{}

// GetGoodCount 获取商品数量
func (exa *UpdfGoodService) GetGoodCount(args request.GetGoodList) (err error, total int64) {
	db := global.GVA_DBList[global.UPDF].Model(&goods.UpdfPayGoods{})

	exa.goodWhere(args, db)

	err = db.Debug().Count(&total).Error
	if err != nil {
		return err, total
	}

	return err, total
}

// GetGoodList 分页获取商品列表
func (exa *UpdfGoodService) GetGoodList(args request.GetGoodList) (err error, list *[]goods.UpdfPayGoods) {
	limit := args.PageSize
	offset := args.PageSize * (args.Page - 1)
	db := global.GVA_DBList[global.UPDF].Model(&goods.UpdfPayGoods{})

	exa.goodWhere(args, db)

	var listDb []goods.UpdfPayGoods
	err = db.Debug().Limit(limit).Offset(offset).Find(&listDb).Error

	return err, &listDb
}

// GetAllGoodList 获取所有商品列表
func (exa *UpdfGoodService) GetAllGoodList(args request.GetGoodList) (err error, list *[]goods.UpdfPayGoods) {
	db := global.GVA_DBList[global.UPDF].Model(&goods.UpdfPayGoods{})

	exa.goodWhere(args, db)

	var listDb []goods.UpdfPayGoods
	err = db.Debug().Find(&listDb).Error

	return err, &listDb
}

// GetGoodCountAndList 获取商品数量及分页信息
func (exa *UpdfGoodService) GetGoodCountAndList(args request.GetGoodList) (err error, list *[]goods.UpdfPayGoods, total int64) {
	limit := args.PageSize
	offset := args.PageSize * (args.Page - 1)
	db := global.GVA_DBList["updf"].Model(&goods.UpdfPayGoods{})

	exa.goodWhere(args, db)
	err = db.Count(&total).Error
	if err != nil {
		return err, list, total
	}

	var listDb []goods.UpdfPayGoods
	err = db.Debug().Limit(limit).Offset(offset).Find(&listDb).Error

	return err, &listDb, total
}

func (exa *UpdfGoodService) goodWhere(args request.GetGoodList, db *gorm.DB) {
	utils.IsExist(db)
}