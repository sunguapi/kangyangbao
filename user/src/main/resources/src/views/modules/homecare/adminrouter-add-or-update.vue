<template>
  <el-dialog
    :title="!dataForm.routerId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="路径" prop="path">
      <el-input v-model="dataForm.path" placeholder="路径"></el-input>
    </el-form-item>
    <el-form-item label="组件名称" prop="commponent">
      <el-input v-model="dataForm.commponent" placeholder="组件名称"></el-input>
    </el-form-item>
    <el-form-item label="组件标题" prop="name">
      <el-input v-model="dataForm.name" placeholder="组件标题"></el-input>
    </el-form-item>
    <el-form-item label="转发地址" prop="redirect">
      <el-input v-model="dataForm.redirect" placeholder="转发地址"></el-input>
    </el-form-item>
    <el-form-item label="目标数据id" prop="metaId">
      <el-input v-model="dataForm.metaId" placeholder="目标数据id"></el-input>
    </el-form-item>
    <el-form-item label="是否隐藏" prop="hidden">
      <el-input v-model="dataForm.hidden" placeholder="是否隐藏"></el-input>
    </el-form-item>
    <el-form-item label="孩子id" prop="childrenId">
      <el-input v-model="dataForm.childrenId" placeholder="孩子id"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          routerId: 0,
          path: '',
          commponent: '',
          name: '',
          redirect: '',
          metaId: '',
          hidden: '',
          childrenId: ''
        },
        dataRule: {
          path: [
            { required: true, message: '路径不能为空', trigger: 'blur' }
          ],
          commponent: [
            { required: true, message: '组件名称不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '组件标题不能为空', trigger: 'blur' }
          ],
          redirect: [
            { required: true, message: '转发地址不能为空', trigger: 'blur' }
          ],
          metaId: [
            { required: true, message: '目标数据id不能为空', trigger: 'blur' }
          ],
          hidden: [
            { required: true, message: '是否隐藏不能为空', trigger: 'blur' }
          ],
          childrenId: [
            { required: true, message: '孩子id不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.routerId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.routerId) {
            this.$http({
              url: this.$http.adornUrl(`/homecare/adminrouter/info/${this.dataForm.routerId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.path = data.adminRouter.path
                this.dataForm.commponent = data.adminRouter.commponent
                this.dataForm.name = data.adminRouter.name
                this.dataForm.redirect = data.adminRouter.redirect
                this.dataForm.metaId = data.adminRouter.metaId
                this.dataForm.hidden = data.adminRouter.hidden
                this.dataForm.childrenId = data.adminRouter.childrenId
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/homecare/adminrouter/${!this.dataForm.routerId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'routerId': this.dataForm.routerId || undefined,
                'path': this.dataForm.path,
                'commponent': this.dataForm.commponent,
                'name': this.dataForm.name,
                'redirect': this.dataForm.redirect,
                'metaId': this.dataForm.metaId,
                'hidden': this.dataForm.hidden,
                'childrenId': this.dataForm.childrenId
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
