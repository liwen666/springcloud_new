#test 1
 @RequestMapping(value = "/{topic}/send",method = RequestMethod.GET)
    public void sendMeessage(
http://localhost:8000/atopic/send

#test2
http://localhost:8000/atopic/getMessage

 @RequestMapping(value = "/{topic}/getMessage",method = RequestMethod.GET)
    @ResponseBody
    public Object getMessage(