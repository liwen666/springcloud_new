//package com.temp.jpa.service.compiler.code;
//
//import com.google.common.cache.Cache;
//import com.google.common.cache.CacheBuilder;
//import jrx.anyest.engine.base.model.resources.function.Function;
//import jrx.anyest.engine.base.model.resources.function.FunctionInfo;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 函数对象构建，将同一个分类下的所有函数封装为一个java类
// * 类名该使用分类信息，但分类无code，暂使用第一个函数的标识+contentCode
// * 方法名为函数的标识code
// *
// * @author xiaohang.hu
// * @date 2020/03/25
// *
// */
//public class FunctionInfoCompilerBuilder {
//
//    /**缓存已经编译过的函数，key值为函数所属分类id，value为对应的编译器 */
//    private static Cache<Integer,ObjectSourceCodeCompiler> codeCompilerCache = CacheBuilder.newBuilder().build();
//
//    public FunctionInfoCompilerBuilder() {
//    }
//
//    public static ObjectSourceCodeCompiler build(List<FunctionInfo> functionInfoList,Integer categoryId, String className,boolean paramNullCheck){
//
//        ObjectSourceCodeCompiler codeCompiler = codeCompilerCache.getIfPresent(categoryId);
//
//        if (codeCompiler != null){
//            return codeCompiler;
//        }
//
//        String javaCode = "";
//        String importPkg = JavaSourceCodeBuilder.defaultImportPackage("\n");
//        String classPkg = JavaCodeExecutor.DEFAULT_PKG;
//
//        List<String> methodList = new ArrayList<>();
//        for (FunctionInfo functionInfo : functionInfoList){
//
//            StringBuilder methods = new StringBuilder();
//            if (functionInfo.getVersionInfo() != null){
//                methods.append(wrapCommentMethod(functionInfo));
//                methods.append("\n");
//                methods.append(methodScript(functionInfo,paramNullCheck));
//                methods.append("\n");
//            }
//            if (methods.length() > 0) {
//                methodList.add(methods.toString());
//            }
//        }
//
//        //没有可编译代码
//        if (methodList.isEmpty()) {
//            return null;
//        }
//
//        javaCode = JavaSourceCodeBuilder.wrapClassCode(className, classPkg, importPkg, true, methodList.toArray(new String[]{}));
//
//        codeCompiler = new ObjectSourceCodeCompiler(javaCode, className,methodList.size());
//
//        codeCompilerCache.put(categoryId,codeCompiler);
//
//        return codeCompiler;
//    }
//
//    /**
//     * 方法注释
//     * @param functionInfo
//     * @return
//     */
//    public static String wrapCommentMethod(FunctionInfo functionInfo){
//        StringBuilder buf = new StringBuilder();
//
//        Function function = (Function) functionInfo.getVersionInfo();
//        buf.append("    ");
//        buf.append("/*--m--*/");
//        buf.append("\n");
//        buf.append("    ");
//        buf.append("// ");
//        buf.append(functionInfo.getResourceId());
//        buf.append(", ");
//        buf.append(function.getReturnValueType());
//        buf.append(", ");
//        buf.append(functionInfo.getCode());
//        buf.append(", ");
//        buf.append(functionInfo.getName());
//
//        return buf.toString();
//    }
//
//    public static String methodScript(FunctionInfo functionInfo, boolean paramNullCheck) {
//
//        Function function = (Function) functionInfo.getVersionInfo();
//        StringBuilder buf = new StringBuilder();
//        String[] paramCode = function.paramCode();
//        Class<?>[] paramType = function.paramType();
//
//        String methodCode = JavaSourceCodeBuilder.methodString(paramCode, paramType, functionInfo.getCode(), function.getScripts(), true, paramNullCheck);
//        buf.append(methodCode);
//        return buf.toString();
//    }
//
//    public static void clearCache(){
//        codeCompilerCache.invalidateAll();
//    }
//}
