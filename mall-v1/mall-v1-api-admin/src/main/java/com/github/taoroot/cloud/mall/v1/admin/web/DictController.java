package com.github.taoroot.cloud.mall.v1.admin.web;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.admin.service.DictDataService;
import com.github.taoroot.cloud.mall.v1.admin.service.DictTypeService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminDictData;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminDictType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dict")
@AllArgsConstructor
public class DictController {

    private final DictTypeService dictTypeService;
    private final DictDataService dictDataService;

    @GetMapping("/types")
    public R typePage(Page<AdminDictType> page) {
        return R.ok(dictTypeService.page(page));
    }

    @PostMapping("/type")
    public R typeSave(@RequestBody AdminDictType adminDictType) {
        return R.ok(dictTypeService.save(adminDictType));
    }

    @DeleteMapping("/type")
    public R typeDel(@RequestParam List<Integer> ids) {

        for (Integer id : ids) {
            int a = dictDataService.count(Wrappers.<AdminDictData>lambdaQuery().eq(AdminDictData::getTypeId, id));
            if (a > 0) {
                return R.errMsg("先删除子集");
            }
        }
        return R.ok(dictTypeService.removeByIds(ids));
    }

    @PutMapping("/type")
    public R typeUpdate(@RequestBody AdminDictType adminDictType) {
        return R.ok(dictTypeService.updateById(adminDictType));
    }

    @GetMapping("/datas")
    public R dataPage(Page<AdminDictData> page, Integer type) {
        return R.ok(dictDataService.page(page,
                Wrappers.<AdminDictData>lambdaQuery().eq(AdminDictData::getTypeId, type)
        ));
    }

    @PostMapping("/data")
    public R dataSave(@RequestBody AdminDictData adminDictData) {
        if (adminDictData.getIsDefault()) {
            dictDataService.update(Wrappers.<AdminDictData>lambdaUpdate()
                    .set(AdminDictData::getIsDefault, false)
                    .eq(AdminDictData::getTypeId, adminDictData.getTypeId()));
        }
        return R.ok(dictDataService.save(adminDictData));
    }

    @DeleteMapping("/data")
    public R dataDel(@RequestParam List<Integer> ids) {
        return R.ok(dictDataService.removeByIds(ids));
    }

    @PutMapping("/data")
    public R dataUpdate(@RequestBody AdminDictData adminDictData) {
        if (adminDictData.getIsDefault()) {
            dictDataService.update(Wrappers.<AdminDictData>lambdaUpdate()
                    .set(AdminDictData::getIsDefault, false)
                    .eq(AdminDictData::getTypeId, adminDictData.getTypeId()));
        }
        return R.ok(dictDataService.updateById(adminDictData));
    }

    @GetMapping("/type/data")
    public R dataByType(@RequestParam String type,
                        @RequestParam(defaultValue = "true") Boolean keyIsNum,
                        @RequestParam(defaultValue = "value") String valueKey,
                        @RequestParam(defaultValue = "label") String labelKey) {
        AdminDictType dictType = dictTypeService.getOne(Wrappers.<AdminDictType>lambdaQuery()
                .eq(AdminDictType::getType, type)
                .eq(AdminDictType::getEnabled, true)
        );
        if (dictType == null) {
            return R.errMsg(String.format("字典类型 [%s] 不存在!", type));
        }

        List<AdminDictData> list = dictDataService.list(Wrappers.<AdminDictData>lambdaQuery()
                .eq(AdminDictData::getTypeId, dictType.getId())
                .eq(AdminDictData::getEnabled, true)
        );

        return R.ok(list.stream().map(item -> {
            HashMap<Object, Object> map = new HashMap<>();
            if (keyIsNum) {
                map.put(valueKey, Integer.parseInt(item.getValue()));
            } else {
                map.put(valueKey, item.getValue());
            }
            map.put(labelKey, item.getLabel());
            return map;
        }).collect(Collectors.toList()));
    }
}
