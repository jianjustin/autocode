package updf

import (
	"admin/global"
	"admin/model/common/request"
	"admin/model/common/response"
	"admin/utils"
	"github.com/gin-gonic/gin"
	"go.uber.org/zap"
)

type ProductApi struct{}

// GetProductList 获取产品列表
func (e *ProductApi) GetProductList(c *gin.Context) {
	var params request.GetProductList
	_ = c.ShouldBindJSON(&params)
	if err := utils.Verify(params, utils.PageInfoVerify); err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}

	err, list, total := updfProductService.GetProductCountAndList(params)
	if err != nil {
		global.GVA_LOG.Error("获取产品列表失败!", zap.Error(err))
		response.FailWithMessage("获取产品列表失败"+err.Error(), c)
		return
	}

	response.OkWithDetailed(response.OrderResult{
		List:     list,
		Total:    total,
		Page:     params.Page,
		PageSize: params.PageSize,
	}, "获取成功", c)
}

func (e *ProductApi) GetSubProductList(c *gin.Context) {
	var params request.GetSubProductList
	_ = c.ShouldBindJSON(&params)
	if err := utils.Verify(params, utils.PageInfoVerify); err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}

	err, list := updfProductService.GetSubProductList(params)
	if err != nil {
		global.GVA_LOG.Error("获取失败!", zap.Error(err))
		response.FailWithMessage("获取失败"+err.Error(), c)
		return
	}
	var total int64
	if params.GetTotal == 1 {
		err, total = updfProductService.GetSubProductCount(params)
		if err != nil {
			global.GVA_LOG.Error("获取失败!", zap.Error(err))
			response.FailWithMessage("获取失败"+err.Error(), c)
			return
		}
	}

	response.OkWithDetailed(response.OrderResult{
		List:  list,
		Total: total,
	}, "获取成功", c)
}
