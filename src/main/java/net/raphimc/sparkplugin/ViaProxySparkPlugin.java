/*
 * This file is part of ViaProxySpark - https://github.com/ViaVersionAddons/ViaProxySpark
 * Copyright (C) 2024-2024 RK_01/RaphiMC and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.raphimc.sparkplugin;

import com.viaversion.viaversion.api.Via;
import me.lucko.spark.common.SparkPlatform;
import me.lucko.spark.common.SparkPlugin;
import me.lucko.spark.common.platform.PlatformInfo;
import net.lenni0451.lambdaevents.EventHandler;
import net.raphimc.viaproxy.ViaProxy;
import net.raphimc.viaproxy.plugins.PluginManager;
import net.raphimc.viaproxy.plugins.ViaProxyPlugin;
import net.raphimc.viaproxy.plugins.events.ConsoleCommandEvent;
import net.raphimc.viaproxy.plugins.events.ViaLoadingEvent;
import net.raphimc.viaproxy.protocoltranslator.viaproxy.ConsoleCommandSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.stream.Stream;

public class ViaProxySparkPlugin extends ViaProxyPlugin implements SparkPlugin {

    private static final Logger LOGGER = LogManager.getLogger("spark");

    private SparkPlatform platform;

    @Override
    public void onEnable() {
        ViaProxy.EVENT_MANAGER.register(this);
    }

    @EventHandler
    public void onViaProxyLoaded(final ViaLoadingEvent event) {
        this.platform = new SparkPlatform(this);
        this.platform.enable();
    }

    @EventHandler
    public void onConsoleCommand(final ConsoleCommandEvent event) {
        if (!event.getCommand().equals(this.getCommandName())) return;
        event.setCancelled(true);

        this.platform.executeCommand(new ViaProxyCommandSender(new ConsoleCommandSender()), event.getArgs());
    }

    @Override
    public Path getPluginDirectory() {
        return new File(PluginManager.PLUGINS_DIR, "spark").toPath();
    }

    @Override
    public String getCommandName() {
        return "spark";
    }

    @Override
    public Stream<ViaProxyCommandSender> getCommandSenders() {
        return Arrays.stream(Via.getPlatform().getOnlinePlayers()).map(ViaProxyCommandSender::new);
    }

    @Override
    public void executeAsync(final Runnable task) {
        Via.getPlatform().runAsync(task);
    }

    @Override
    public void log(final Level level, final String msg) {
        if (level == Level.INFO) {
            LOGGER.info(msg);
        } else if (level == Level.WARNING) {
            LOGGER.warn(msg);
        } else if (level == Level.SEVERE) {
            LOGGER.error(msg);
        } else {
            throw new IllegalArgumentException(level.getName());
        }
    }

    @Override
    public void log(final Level level, final String msg, Throwable throwable) {
        if (level == Level.INFO) {
            LOGGER.info(msg, throwable);
        } else if (level == Level.WARNING) {
            LOGGER.warn(msg, throwable);
        } else if (level == Level.SEVERE) {
            LOGGER.error(msg, throwable);
        } else {
            throw new IllegalArgumentException(level.getName());
        }
    }

    @Override
    public PlatformInfo getPlatformInfo() {
        return new ViaProxyPlatformInfo();
    }

}
