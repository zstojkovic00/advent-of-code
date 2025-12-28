package zeljko.com

import readInputLines

var totalPaths = 0

fun main() {
    val lines = readInputLines(false, 2025, 11)
    val devicesOutputs = mutableMapOf<String, List<String>>()

    for (line in lines) {
        val parts = line.split(":")
        devicesOutputs[parts[0]] = parts[1].trim().split(" ")
    }

    val connectedDevicesStart = devicesOutputs["you"]

    for (device in connectedDevicesStart!!){
        findPathsToOut(device, devicesOutputs)
    }

    println(totalPaths)
}

fun findPathsToOut(device: String, devicesOutputs: MutableMap<String, List<String>>) {
    println(device)
    val connectedDevices = devicesOutputs[device]

    if(connectedDevices?.get(0) == "out"){
        totalPaths++
        return
    }

    println(connectedDevices)
    for (connectedDevice in connectedDevices!!){
        findPathsToOut(connectedDevice, devicesOutputs)
    }

}