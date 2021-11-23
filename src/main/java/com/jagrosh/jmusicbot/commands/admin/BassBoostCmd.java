/*
 * Copyright 2016 John Grosh <john.a.grosh@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jagrosh.jmusicbot.commands.admin;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.AdminCommand;

/**
 * @author John Grosh <john.a.grosh@gmail.com>
 */
public class BassBoostCmd extends AdminCommand {
    public BassBoostCmd(Bot bot) {
        this.name = "bassboost";
        this.help = "sets bass boost";
        this.aliases = bot.getConfig().getAliases(this.name);
    }

    @Override
    public void execute(CommandEvent event) {
        AudioHandler handler = (AudioHandler) event.getGuild().getAudioManager().getSendingHandler();

        if (handler == null) {
            event.replyError("Unknown error!");
            return;
        } else if (event.getArgs().isEmpty()) {
            event.replyError("Usage: bassboost <level>");
            return;
        }

        int boost = Integer.parseInt(event.getArgs());

        if (boost < 0 || boost > 100) {
            event.reply(event.getClient().getError() + " Bass boost must be a valid integer between 1 and 100!");
            return;
        }

        handler.setBandGains(
                0.004f * boost,
                0.005f * boost,
                0.005f * boost,
                0.004f * boost,
                0.003f * boost,
                0.002f * boost,
                0.00f,
                0.00f,
                0.00f,
                0.00f,
                0.00f,
                0.00f,
                0.00f,
                0.00f,
                0.00f
        );
        if (boost == 0) {
            event.replySuccess("Bass boost disabled!");
        } else {
            event.replySuccess("Successfully set bass boost to " + event.getArgs() + ".");
        }
    }
}
