interface CommandExecutor {
    fun executeCommand(command: String)
    fun stopProcess()
}