// GetProductCount 获取产品数量
func (exa *UpdfProductService) GetProductCount(args request.GetProductList) (err error, total int64) {
db := global.GVA_DBList[global.UPDF].Model(&goods.UpdfPayProducts{})

exa.productWhere(args, db)

err = db.Debug().Count(&total).Error
if err != nil {
return err, total
}

return err, total
}

// GetProductList 分页获取产品列表
func (exa *UpdfProductService) GetProductList(args request.GetProductList) (err error, list *[]goods.UpdfPayProducts) {
limit := args.PageSize
offset := args.PageSize * (args.Page - 1)
db := global.GVA_DBList[global.UPDF].Model(&goods.UpdfPayProducts{})

exa.productWhere(args, db)

var listDb []goods.UpdfPayProducts
err = db.Debug().Limit(limit).Offset(offset).Find(&listDb).Error

return err, &listDb
}

// GetProductCountAndList 获取产品数量及分页信息
func (exa *UpdfProductService) GetProductCountAndList(args request.GetProductList) (err error, list *[]goods.UpdfPayProducts, total int64) {
limit := args.PageSize
offset := args.PageSize * (args.Page - 1)
db := global.GVA_DBList["updf"].Model(&goods.UpdfPayProducts{})

exa.productWhere(args, db)
err = db.Count(&total).Error
if err != nil {
return err, list, total
}

var listDb []goods.UpdfPayProducts
err = db.Debug().Limit(limit).Offset(offset).Find(&listDb).Error

return err, &listDb, total
}