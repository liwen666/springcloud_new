//package com.temp.jpa.service.compiler.code;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.cache.Cache;
//import com.google.common.cache.CacheBuilder;
//import jrx.anyest.engine.base.enums.field.FieldType;
//import jrx.anyest.engine.base.model.field.BaseField;
//import jrx.anyest.engine.base.model.field.ObjectField;
//import jrx.anyest.engine.base.model.field.StatField;
//import jrx.anyest.engine.base.model.field.derive.BaseDeriveField;
//import jrx.anyest.engine.base.model.field.derive.DeriveScriptField;
//import jrx.anyest.engine.base.model.field.derive.DeriveTransform;
//import org.apache.commons.collections.CollectionUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 字段对象构建，将一个对象中的脚本字段合并为一个java类
// * 类名称为object code
// * 方法名称为字段field code
// *
// * @author
// * @version 2.0
// * @date 2020/3/20
// */
//public class ObjectSourceCodeCompilerBuilder {
//
//    private static final Logger logger = LoggerFactory.getLogger(ObjectSourceCodeCompilerBuilder.class);
//
//    /**缓存已经编译过的对象类，key值为objectInfoCode，value为对应的编译器 */
//    private static Cache<String,ObjectSourceCodeCompiler> codeCompilerCache = CacheBuilder.newBuilder().build();
//
//    private ObjectSourceCodeCompilerBuilder() {
//    }
//
//    public static ObjectSourceCodeCompiler build(List<? extends ObjectField> deriveFields, String objectInfoCode, boolean paramNullCheck, boolean scriptOnly) {
//
//        ObjectSourceCodeCompiler codeCompiler = codeCompilerCache.getIfPresent(objectInfoCode);
//
//        if(codeCompiler != null){
//            return codeCompiler;
//        }
//
//        String javaCode = "";
//        String importPkg = JavaSourceCodeBuilder.defaultImportPackage("\n");
//        String classPkg = JavaCodeExecutor.DEFAULT_PKG;
//
//        List<String> methodList = new ArrayList<>();
//        for (ObjectField objectField : deriveFields) {
//
//            if (!(objectField instanceof BaseDeriveField)) {
//                continue;
//            }
//
//            BaseDeriveField baseDeriveField = (BaseDeriveField) objectField;
//
//            StringBuilder methods = new StringBuilder();
//            // 没有参数不进行编译
//            if (baseDeriveField.getFieldType() == FieldType.SCRIPT_FIELD && CollectionUtils.isNotEmpty(((DeriveScriptField) baseDeriveField).getParamFields())) {
//                methods.append(wrapCommentMethod(baseDeriveField));
//                methods.append("\n");
//                methods.append(methodScriptField((DeriveScriptField) baseDeriveField, paramNullCheck));
//                methods.append("\n");
//            } else if (baseDeriveField.getFieldType() == FieldType.TRANSFORM_FIELD && !scriptOnly) {
//                methods.append(wrapCommentMethod(baseDeriveField));
//                methods.append("\n");
//                methods.append(methodTransFormField((DeriveTransform) baseDeriveField));
//                methods.append("\n");
//            } else if (baseDeriveField.getFieldType() == FieldType.STAT_FIELD && !scriptOnly) {
//                methods.append(wrapCommentMethod(baseDeriveField));
//                methods.append("\n");
//                methods.append(methodStatField((StatField) baseDeriveField));
//                methods.append("\n");
//            }
//            if (methods.length() > 0) {
//                methodList.add(methods.toString());
//            }
//        }//end for
//
//        //没有可编译代码
//        if (methodList.isEmpty()) {
//            return null;
//        }
//
//        javaCode = JavaSourceCodeBuilder.wrapClassCode(objectInfoCode, classPkg, importPkg, true, methodList.toArray(new String[]{}));
//
//        codeCompiler = new ObjectSourceCodeCompiler(javaCode, objectInfoCode,methodList.size());
//
//        codeCompilerCache.put(objectInfoCode,codeCompiler);
//
//        return codeCompiler;
//    }//end codeCompiler
//
//
//    public static String wrapCommentMethod(BaseDeriveField deriveField) {
//        StringBuilder buf = new StringBuilder();
//        buf.append("    ");
//        buf.append("/*--m--*/");
//        buf.append("\n");
//        buf.append("    ");
//        buf.append("// ");
//        buf.append(deriveField.getBid());
//        buf.append(", ");
//        buf.append(deriveField.getValueType());
//        buf.append(", ");
//        buf.append(deriveField.getFieldType());
//        buf.append(", ");
//        buf.append(deriveField.getUpdateMode());
//        buf.append(", ");
//        buf.append(deriveField.getComputePeriod());
//        buf.append(", ");
//        buf.append(deriveField.getFieldCode());
//        buf.append(", ");
//        buf.append(deriveField.getFieldName());
//        buf.append(", ");
//        buf.append(deriveField.getReferFields().stream().map(BaseField::getBid).collect(Collectors.joining(";")));
//
//        return buf.toString();
//    }
//
//
//    public static String methodScriptField(DeriveScriptField scriptField, boolean paramNullCheck) {
//        StringBuilder buf = new StringBuilder();
//        String[] paramCode = scriptField.paramCode();
//        Class<?>[] paramType = scriptField.paramType();
//
//        String methodCode = JavaSourceCodeBuilder.methodString(paramCode, paramType, scriptField.getFieldCode(), scriptField.getScripts(), true, paramNullCheck);
//        buf.append(methodCode);
//        return buf.toString();
//    }
//
//    public static String methodTransFormField(DeriveTransform deriveTransform) {
//        return wrapMethod(JSON.toJSONString(deriveTransform), deriveTransform.getFieldCode(), deriveTransform.getReferFields());
//    }
//
//    public static String methodStatField(StatField statField) {
//        return wrapMethod(JSON.toJSONString(statField), statField.getFieldCode(), statField.getReferFields());
//    }
//
//    /**
//     * 先简单json字符串处理
//     *
//     * @param content
//     * @param method
//     * @param inputFields
//     * @return
//     */
//    private static String wrapMethod(String content, String method, List<ObjectField> inputFields) {
//        StringBuilder buf = new StringBuilder();
//        buf.append("    ");
//        buf.append("public static Object ");
//        buf.append(method);
//        buf.append("(");
//        for (int i = 0; i < inputFields.size(); i++) {
//            ObjectField param = inputFields.get(i);
//            buf.append(param.getValueType().getValueClazz().getSimpleName());
//            buf.append(" ");
//            buf.append(param.getFieldCode());
//            if (i < inputFields.size() - 1) {
//                buf.append(", ");
//            }
//        }
//        /**/
//        buf.append(") {\n");
//        buf.append("        ");
////        buf.append("/*String jsonObject = \"");
//        buf.append("/* ");
//        buf.append(content);
//        buf.append("\" */");
//        buf.append("\n");
//        buf.append("        ");
//        buf.append("return null;\n");
//
//        buf.append("}\n");
//
//        return buf.toString();
//    }
//
//
//}
