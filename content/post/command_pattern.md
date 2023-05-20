---
title: "Command Pattern in Python"
date: 2021-04-01
lastmod: 2021-04-01
draft: false
tags: ["Python","Command"]
categories: ["Design Pattern"]
---




## Command
``` Python

"""
This is a Command Class
"""
class Command(ABC):
    """
    The Command interface declares a method for executing a command.
    """

    @abstractmethod
    def execute(self) -> None:
        pass


    @abstractmethod
    def undo(self) -> None:
        pass
		
```
## LightOnCommand
``` Python

class LightOnCommand(Command):
    """ 
    We need cohersion the Receiver and implement real command object 
    """


    def __init__(self, receiver: Receiver, a: str) -> None:
        self._receiver = receiver
        self._a = a

    def execute(self) -> None:
        self._receiver.on
        # print()

    def undo(self) -> None:
        self._receiver.off

```
## LightOffCommand
``` Python

class LightOffCommand(Command):
    """ 
    We need cohersion the Receiver and implement real command object 
    """


    def __init__(self, receiver: Receiver, a: str) -> None:
        self._receiver = receiver
        self._a = a

    def execute(self) -> None:
        self._receiver.off
        # print()

    def undo(self) -> None:
        self._receiver.on

```
## Receiver
``` Python
class Receiver(object):
    
    """
    The Receiver classes contain some important business logic. They know how to
    perform all kinds of operations, associated with carrying out a request. In
    fact, any class may serve as a Receiver.

    implement turn on and turn off Receiver 
    """

    def on(self, a: str) -> None:
        print(f"\n turn on the Light ({a}.)", end="" )

    
    
    def off(self, a: str) -> None:
        print(f"\n turn off the Light ({a}.)", end="" )
```
## Invoker
``` Python
class NoCommand(Command):

    def execute(self):
        print('Command Not Found')

    def undo(self):
        print('Command Not Found')


class Invoker:
    """
    The Invoker is associated with one or several commands. It sends a request
    to the command.
    """

    """
    Initialize commands.
    """


    def __init__(self) -> None:

         self.on_commands = [NoCommand() for i in range(7)]
         self.off_commands = [NoCommand() for i in range(7)]
         self.undo_command = None

    def set_command(self, slot, on_command, off_command):
       
        self.on_commands[slot] = on_command
        self.off_commands[slot] = off_command

    def on_button_was_pressed(self, slot):
        command = self.on_commands[slot]
        command.execute()
        self.undo_command = command

    def off_button_was_pressed(self, slot):
        command = self.off_commands[slot]
        command.execute()
        self.undo_command = command

    def undo_button_was_pressed(self):
        self.undo_command.undo()
    
    def __str__(self):
        for i in range(7):
            print('[slot %d] %s %s' % (i,
                                       self.on_commands[i].__class__.__name__,
                                       self.off_commands[i].__class__.__name__))
        return ''
    

```
## Client
``` Python

if __name__ == "__main__":
    """
    The client code can parameterize an invoker with any commands.
    """

    invoker = Invoker()

    living_room_light = Receiver()
    living_room_light_on = LightOnCommand(living_room_light, "Living Room Light")
    living_room_light_off = LightOffCommand(living_room_light, "Living Room Light")


    invoker.set_command(0,living_room_light_on, living_room_light_off )

    print(invoker)
    invoker.on_button_was_pressed(0)
    invoker.off_button_was_pressed(0)


```
- [x] Alreadu finish the implement: Turn on/off the light
- [ ]  We need to have more scenarios.

|Class Name | Description | Abstract |
|:------    |---------:    |:--:  |
|Command  |  Interface which need implemented by sub-class    |  Yes  |
|LightOnCommand      |   Some commands can implement simple operations on their own.       |  No  |
|LightOffCommand      |       |  No  |
|Receiver      |  The Receiver classes contain some important business logic.      |  Yes  |
|Invoker      |  The Invoker is associated with one or several commands.      |  No  |
|Client      | The client code can parameterize an invoker with any commands.      |  No  |



[[Source Code]](https://gist.github.com/gusibi/e66134218fdecff59e5690298d657c26)


[Achilles' Blog](http://www.yaolugnu.com/)