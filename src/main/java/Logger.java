class Logger implements LoggerInterface{
  @Override
  public void log(String string) {
    System.out.println(string);
  }
}
